import React from 'react';

interface IBlogProps {
    title: string;
    author: string;
}

export default class Blog extends React.Component<IBlogProps> {
    constructor(props: IBlogProps) {
        super(props);
    }

    public render() {
        return (
            <div>
                {this.props.title} {this.props.author}
            </div>
        );
    }
}
