const removeAllRefreshers = () => {
    const intervalId = setInterval(() => {
    }, 1);
    for (let i = 1; i < intervalId; i++) {
        clearInterval(i);
    }
};

export {removeAllRefreshers};

export const REFRESH_RATE = 1000;

