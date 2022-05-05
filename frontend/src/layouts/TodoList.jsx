import React from 'react';
import { ListTodo } from '../components/ListTodo.jsx';
import useFetch from '../utils/useFetch.jsx';

const TodoList = ({ API }) => {
  const { data: todoList, loading, error } = useFetch(`${API}/todo-list`);
  const { data: todo } = useFetch(`${API}/todo`);

  if (!todoList)
    return (
      <>
        <p>Nothing... Go add one</p>
      </>
    );
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  const todoListItems = todoList.data.map((todoListItem) => {
    const list = todo.data.filter(
      (todoItem) => todoItem.todoListId === todoListItem.id
    );
    return { ...todoListItem, list };
  });

  return (
    <>
      <h1>Todo list</h1>
      {console.log(todoListItems)}
      {todoListItems.map((todoList) => (
        <ListTodo list={todoList.list} todos={todoList.todos} />
      ))}
    </>
  );
};

export { TodoList };
