import React from 'react';

interface INotificationProps {
    message: string | null;
    type: string;
}

const Notification: React.FC<INotificationProps> = props => {
    if (props.message === null) {
        return null;
    }
    if (props.type === 'error') {
        return <div className="error">{props.message}</div>;
    } else {
        return <div className="info">{props.message}</div>;
    }
};

export default Notification;
