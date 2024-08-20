import React from 'react';

const GameStatus = ({ currentQuestionNumber, prizeAmount, guarantee }) => {
    return (
        <div className="game-status">
            <h2>Question {currentQuestionNumber}</h2>
            <h3>Prize: ${prizeAmount}</h3>
            <h3>Guaranteed Amount: ${guarantee}</h3>
        </div>
    );
};

export default GameStatus;
