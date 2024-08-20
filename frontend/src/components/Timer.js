import React, { useEffect, useState } from 'react';

const Timer = ({ timeLimit, onTimeUp }) => {
    const [timeLeft, setTimeLeft] = useState(timeLimit);

    useEffect(() => {
        setTimeLeft(timeLimit); 
    }, [timeLimit]);

    useEffect(() => {
        if (timeLeft === 0) {
            onTimeUp();
        } else {
            const timerId = setInterval(() => {
                setTimeLeft((prevTime) => prevTime - 1);
            }, 1000);
            return () => clearInterval(timerId);
        }
    }, [timeLeft, onTimeUp]);

    return <div className="timer">Time left: {timeLeft}s</div>;
};

export default Timer;
