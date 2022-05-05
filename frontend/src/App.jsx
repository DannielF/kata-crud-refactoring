import React from 'react';
import { TodoContext } from './context/index';
import { TodoListForm } from './layouts/TodoListForm';

function App() {
  const { addTodoList, API } = React.useContext(TodoContext);
  return (
    <>
      <TodoListForm add={addTodoList} />
    </>
  );
}

export default App;
