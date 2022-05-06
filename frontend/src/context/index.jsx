import React, { createContext } from 'react';

const TodoContext = createContext({});

const TodoProvider = ({ children }) => {
  const API = 'http://localhost:8080';

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
      await fetch(`${API}/todo-list`, optionsFetch);
    } catch (error) {
      console.error('Fetch -POST- error', error);
    }
  };

  const deleteTodoList = async (id) => {
    let optionsFetch = {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      await fetch(`${API}/todo-list/${id}`, optionsFetch);
    } catch (error) {
      console.error('Fetch -DELETE- error', error);
    }
  };

  const addTodo = async (data, list) => {
    const bodyParsed = {
      name: data.name,
      done: false,
      todoListId: list.id,
    };

    let optionsFetch = {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyParsed),
    };

    try {
      await fetch(`${API}/todo`, optionsFetch);
    } catch (error) {
      console.error('Fetch -POST- error', error);
    }
  };

  const editTodo = async (data, id) => {
    const bodyParsed = {
      name: data.name,
    };

    let optionsFetch = {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(bodyParsed),
    };

    try {
      await fetch(`${API}/todo/${id}`, optionsFetch);
    } catch (error) {
      console.error('Fetch -PUT- error', error);
    }
  };

  const deleteTodo = async (id) => {
    let optionsFetch = {
      method: 'DELETE',
      mode: 'cors',
    };

    try {
      await fetch(`${API}/todo/${id}`, optionsFetch);
    } catch (error) {
      console.error('Fetch -DELETE- error', error);
    }
  };

  return (
    <TodoContext.Provider
      value={{
        addTodoList,
        deleteTodoList,
        addTodo,
        editTodo,
        deleteTodo,
        API,
      }}
    >
      {children}
    </TodoContext.Provider>
  );
};

export { TodoContext, TodoProvider };
