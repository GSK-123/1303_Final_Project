import React from 'react';

const Question = ({ question, onAnswer, reducedAnswers }) => {
    const answersToShow = reducedAnswers.length > 0 ? reducedAnswers : question.answers;

    return (
        <div className="question-area">
            <div className="question">
                <h3>{question.text}</h3>
            </div>
            <div className="answers">
                {answersToShow.map((answer, index) => (
                    <button
                        key={index}
                        className="answer-button"
                        onClick={() => onAnswer(answer)}
                    >
                        {String.fromCharCode(65 + index)}: {answer}
                    </button>
                ))}
            </div>
        </div>
    );
};

export default Question;
