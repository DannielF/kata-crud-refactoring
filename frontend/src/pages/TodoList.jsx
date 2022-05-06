import React from 'react';
import { ListTodo } from '../components/ListTodo.jsx';
import { TodoContext } from '../context/index';

/**
 * List a todoList and todo items
 * @returns {JSX.Element}
 * @version 0.0.1
 * @since 0.0.1
 */
const TodoList = () => {
  const { todoList, todo, error } = React.useContext(TodoContext);

  if (!todoList) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  const todoListItems = todoList.data.map((todoListItem) => {
    const list = todo.data.filter(
      (todoItem) => todoItem.todoListId === todoListItem.id
    );
    return { ...todoListItem, list };
  });

  return (
    <>
      {todoList.data.length == 0 && <p>Nothing... Go add one</p>}
      <h1>Todo list</h1>
      {todoListItems.map((todoList, index) => (
        <ListTodo key={index} list={todoList} todos={todoList.list} />
      ))}
    </>
  );
};

export { TodoList };
