import React from 'react';
interface INotificationProps {
    message: string | null;
    type: string;
}

export default class Notification extends React.Component<INotificationProps> {
    public render() {
        if (this.props.message === null) {
            return null;
        }
        if (this.props.type === 'error') {
            return <div className="error">{this.props.message}</div>;
        } else {
            return <div className="info">{this.props.message}</div>;
        }
    }
}
