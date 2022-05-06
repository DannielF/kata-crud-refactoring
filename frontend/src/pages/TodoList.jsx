import React from 'react';
import { ListTodo } from '../components/ListTodo.jsx';
import useFetch from '../utils/useFetch.jsx';

const TodoList = ({ API }) => {
  const todoList = useFetch(`/todo-list`);
  const todo = useFetch(`/todo`);

  if (!todoList) {
    return <></>;
  }

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
