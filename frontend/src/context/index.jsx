import React, { createContext } from 'react';
import useFetch from '../utils/useFetch.jsx';

const TodoContext = createContext({});

const TodoProvider = ({ children }) => {
  const API = 'http://localhost:8080';
  const { data, loading, error } = useFetch(`${API}/todo-list`);
  console.log(data);
  console.log(loading);
  console.log(error);

  const addTodoList = async (data) => {
    let optionsFetch = {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: data,
      }),
    };

    try {
      await fetch(`${API}/todo-list`, options);
    } catch (error) {
      console.error('Fetch -POST- error', error);
    }
  };

  return (
    <TodoContext.Provider value={{ addTodoList, API }}>
      {children}
    </TodoContext.Provider>
  );
};

export { TodoContext, TodoProvider };
