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
    <div className="flex flex-col mt-5 items-center w-1/2">
      {todoList.data.length == 0 && (
        <p className="text-lg">Nothing... Go add one</p>
      )}
      {todoList.data.length > 0 && (
        <h1 className="text-2xl font-semibold mt-5">Todo list</h1>
      )}
      {todoListItems.map((todoList, index) => (
        <ListTodo key={index} list={todoList} todos={todoList.list} />
      ))}
    </div>
  );
};

export { TodoList };
