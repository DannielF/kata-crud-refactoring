import React from 'react';
import { TodoContext } from './context/index';
import { TodoList } from './pages/TodoList';
import { TodoListForm } from './pages/TodoListForm';

/**
 * App
 * @returns {JSX.Element}
 * @version 0.0.1
 * @since 0.0.1
 */
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
