import React from 'react';
import { TodoContext } from './context/index';
import { TodoList } from './pages/TodoList';
import { TodoListForm } from './pages/TodoListForm';

function App() {
  const { addTodoList, API } = React.useContext(TodoContext);
  return (
    <>
      <TodoListForm addTodoList={addTodoList} />
      <TodoList />
    </>
  );
}

export default App;
