import React from 'react';
import { FormTodo } from './components/FormTodo';
import { ListTodos } from './components/ListTodos';
import { TodoContext } from './context/index';

function App() {
  const { state, dispatch, API } = React.useContext(TodoContext);
  return (
    <>
      <ListTodos dispatch={dispatch} state={state} api={API} />
      <FormTodo dispatch={dispatch} state={state} api={API} />
    </>
  );
}

export default App;
