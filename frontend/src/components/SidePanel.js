import React from 'react';
import '../App.css';

const SidePanel = ({ currentQuestionIndex }) => {
    const prizeAmounts = [
        "$100", "$200", "$300", "$500", "$1,000",
        "$2,000", "$4,000", "$8,000", "$16,000", "$32,000",
        "$64,000", "$125,000", "$250,000", "$500,000", "$1,000,000"
    ];

    return (
        <div className="side-panel">
            <ul className="prize-list">
                {prizeAmounts.map((amount, index) => (
                    <li key={index} className={`prize-item ${index === currentQuestionIndex ? 'active' : ''}`}>
                        <span className="question-number">{index + 1}</span>
                        <span className="prize-amount">{amount}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default SidePanel;
