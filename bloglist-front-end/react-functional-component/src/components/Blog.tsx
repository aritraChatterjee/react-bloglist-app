import React from 'react';

interface IBlogProps {
    title: string;
    author: string;
}

const Blog: React.FC<IBlogProps> = props => (
    <div>
        {props.title} {props.author}
    </div>
);

export default Blog;
