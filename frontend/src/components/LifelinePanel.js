import React from 'react';

const LifelinePanel = ({ usedLifelines, onUseLifeline }) => {
    const lifelines = [
        { key: "fiftyFifty", label: "50-50" },
        { key: "flipTheQuestion", label: "Flip-The-Question" },
        { key: "doubleDip", label: "Double-Dip" },
        { key: "skipQuestion", label: "Skip-Question" }
    ];

    return (
        <div className="lifeline-panel">
            {lifelines.map((lifeline) => (
                <button
                    key={lifeline.key}
                    className="lifeline-button"
                    disabled={usedLifelines[lifeline.key]}
                    onClick={() => onUseLifeline(lifeline.key)}
                    title={usedLifelines[lifeline.key] ? "Already Used" : ""}
                >
                    {lifeline.label} {usedLifelines[lifeline.key] ? "(Used)" : ""}
                </button>
            ))}
        </div>
    );
};

export default LifelinePanel;
