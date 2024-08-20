import React, { useEffect, useState } from 'react';
import GameStatus from './components/GameStatus';
import Timer from './components/Timer';
import LifelinePanel from './components/LifelinePanel';
import Question from './components/Question';
import './App.css';  
import SidePanel from './components/SidePanel';


const App = () => {
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [lifelinesUsed, setLifelinesUsed] = useState({
        fiftyFifty: false,
        flipTheQuestion: false,
        doubleDip: false,
        skipQuestion: false
    });
    const [prizeAmount, setPrizeAmount] = useState(0);
    const [guarantee, setGuarantee] = useState(0);
    const [timeLimit, setTimeLimit] = useState(30);
    const [reducedAnswers, setReducedAnswers] = useState([]);
    const [currentGameId, setCurrentGameId] = useState(null);
    const [activeLifeline, setActiveLifeline] = useState(null); 
    const [doubleDipAttempt, setDoubleDipAttempt] = useState(0); 
    const [timerKey, setTimerKey] = useState(Date.now());
    const [usedQuestionIds, setUsedQuestionIds] = useState([]);

    const prizeValues = [100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000];
    const guaranteedAmounts = { 5: 1000, 10: 32000, 15: 1000000 };

    

    useEffect(() => {
        startNewGame();
    }, []);

    useEffect(() => {
      if (questions.length > 0) {
          setPrizeAmount(prizeValues[currentQuestionIndex]);
          setTimeLimit(questions[currentQuestionIndex].timeLimit);
          setTimerKey(Date.now());
          checkAndUpdateGuarantee(currentQuestionIndex + 1);
      }
    }, [currentQuestionIndex, questions]);
  

    const startNewGame = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/games', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({}),
            });
            const newGame = await response.json();
            setCurrentGameId(newGame.id);
            loadQuestions();
        } catch (error) {
            console.error("Failed to start a new game. Please try again.");
        }
    };

    const loadQuestions = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/questions/random');
            const data = await response.json();
            setQuestions(data);
            setCurrentQuestionIndex(0);
            setUsedQuestionIds([data[0].id]);
        } catch (error) {
            console.error("Failed to load questions. Please try again.");
        }
    };

    const checkAndUpdateGuarantee = (questionNumber) => {
      if (guaranteedAmounts[questionNumber - 1] && guaranteedAmounts[questionNumber - 1] > guarantee) {
          setGuarantee(guaranteedAmounts[questionNumber - 1]);
          alert(`Congratulations! Your guaranteed prize is now ${guaranteedAmounts[questionNumber - 1]}`);
      }
    };
  
  

    const handleAnswer = (answer) => {
      const isCorrect = answer === questions[currentQuestionIndex].correctAnswer;
  
      if (activeLifeline === "doubleDip" && !isCorrect && doubleDipAttempt < 1) {
          alert("Incorrect! You have one more attempt due to Double Dip.");
          setDoubleDipAttempt(doubleDipAttempt + 1); 
      } else if (activeLifeline === "doubleDip" && !isCorrect && doubleDipAttempt >= 1) {
          alert("Incorrect! You've used both attempts with Double Dip. Game over.");
          handleGameOver();
      } else if (isCorrect) {
          checkAndUpdateGuarantee(currentQuestionIndex + 1);
  
          if (currentQuestionIndex < 14) {
              setCurrentQuestionIndex(currentQuestionIndex + 1);
              resetLifelineState(); 
          } else {
              alert("Congratulations! You have won the game!");
              resetGame();
          }
      } else {
          handleGameOver();
      }
    };
  
    const resetLifelineState = () => {
      setReducedAnswers([]);
      setActiveLifeline(null);
      setDoubleDipAttempt(0);
      setTimerKey(Date.now()); 
    };
  

    const handleTimeUp = () => {
        alert("Time's up! Game Over.");
        handleGameOver();
    };

    const handleUseLifeline = async (lifeline) => {
      if (!currentGameId) {
          console.error("Game ID is not set.");
          return;
      }

      if ((lifeline === "fiftyFifty" && activeLifeline === "doubleDip") ||
          (lifeline === "doubleDip" && activeLifeline === "fiftyFifty")) {
          alert("You cannot use Double Dip and 50-50 on the same question.");
          return;
      }
  
      try {
          const response = await fetch(`http://localhost:8080/api/games/${currentGameId}/lifeline?lifelineType=${lifeline}`, {
              method: 'POST'
          });
          const data = await response.json();
  
          setLifelinesUsed({
              fiftyFifty: data.lifelinesUsed.fiftyFifty,
              flipTheQuestion: data.lifelinesUsed.flipTheQuestion,
              doubleDip: data.lifelinesUsed.doubleDip,
              skipQuestion: data.lifelinesUsed.skipQuestion
          });
  
          if (lifeline === "fiftyFifty" && data.lifelinesUsed.fiftyFifty) {
              setActiveLifeline("fiftyFifty");
              FiftyFifty();
          } else if (lifeline === "doubleDip" && data.lifelinesUsed.doubleDip) {
              setActiveLifeline("doubleDip");
          } else if (lifeline === "flipTheQuestion" && data.lifelinesUsed.flipTheQuestion) {
              flipCurrentQuestion();
          } else if (lifeline === "skipQuestion" && data.lifelinesUsed.skipQuestion) {
              skipCurrentQuestion();
          }
      } catch (error) {
          console.error("Failed to use lifeline. Please try again.");
      }
    };
  

    const FiftyFifty = () => {
        const currentQuestion = questions[currentQuestionIndex];
        const incorrectAnswers = currentQuestion.answers.filter(answer => answer !== currentQuestion.correctAnswer);

        const answersToRemove = incorrectAnswers.sort(() => Math.random() - 0.5).slice(0, 2);
        const remainingAnswers = currentQuestion.answers.filter(answer => !answersToRemove.includes(answer));

        setReducedAnswers(remainingAnswers);
    };

    const skipCurrentQuestion = () => {
        if (questions[currentQuestionIndex].difficulty !== 1) {
            alert("Skip-Question can only be used on difficulty level 1 questions.");
            return;
        }
        if (currentQuestionIndex < 14) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
            resetLifelineState();
        }
    };

    const flipCurrentQuestion = () => {
      const currentDifficulty = questions[currentQuestionIndex].difficulty;
      const availableQuestions = questions.filter(q => q.difficulty === currentDifficulty && !usedQuestionIds.includes(q.id));
      
      if (availableQuestions.length > 0) {
          const newQuestion = availableQuestions[Math.floor(Math.random() * availableQuestions.length)];
          setQuestions(prevQuestions => {
              const updatedQuestions = [...prevQuestions];
              updatedQuestions[currentQuestionIndex] = newQuestion;
              return updatedQuestions;
          });
          setUsedQuestionIds([...usedQuestionIds, newQuestion.id]);
          resetLifelineState(); 
      } else {
          alert("No more available questions of this difficulty level to flip to.");
      }
  };
  

    const fetchNewQuestion = async (difficulty) => {
        try {
            const response = await fetch(`http://localhost:8080/api/questions/random?difficulty=${difficulty}`);
            const newQuestions = await response.json();
            const newQuestion = newQuestions.find(q => !usedQuestionIds.includes(q.id));

            if (newQuestion) {
                setUsedQuestionIds([...usedQuestionIds, newQuestion.id]);
                return newQuestion;
            }
            return null;
        } catch (error) {
            console.error("Failed to fetch new question. Please try again.");
            return null;
        }
    };

    const handleGameOver = () => {
        const restartGame = window.confirm("Game Over. Do you want to restart?");
      
        if (restartGame) {
            resetGame();
        } else {
            setCurrentQuestionIndex(-1); 
            setTimeLimit(0); 
        }
    };

    const resetGame = () => {
        setGuarantee(0);
        setPrizeAmount(0);
        setLifelinesUsed({
            fiftyFifty: false,
            flipTheQuestion: false,
            doubleDip: false,
            skipQuestion: false
        });
        resetLifelineState();
        setCurrentQuestionIndex(0); 
        setTimeLimit(30); 
        setTimerKey(Date.now()); 
        loadQuestions(); 
    };

    if (questions.length === 0) return <div>Loading...</div>;

    return (
      <div className="App">
        <SidePanel currentQuestionNumber={currentQuestionIndex + 1} />
        <GameStatus currentQuestionNumber={currentQuestionIndex + 1} prizeAmount={prizeAmount} guarantee={guarantee} />
        <Timer key={timerKey} timeLimit={timeLimit} onTimeUp={handleTimeUp} />
        <LifelinePanel usedLifelines={lifelinesUsed} onUseLifeline={handleUseLifeline} />
        <Question question={questions[currentQuestionIndex]} onAnswer={handleAnswer} reducedAnswers={reducedAnswers} />
      </div>
    );
};

export default App;
