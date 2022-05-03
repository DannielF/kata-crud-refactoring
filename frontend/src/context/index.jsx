import React, { createContext, useReducer } from 'react';

const initialValue = {
  list: [],
};

const TodoContext = createContext(initialValue);

const TodoProvider = ({ children }) => {
  const API = 'http://localhost:8080/todo';

  const reducer = (state, action) => {
    switch (action.type) {
      case 'delete-item':
        const listFiltered = state.list.filter((item) => item.id !== action.id);
        return { ...state, list: listFiltered };
      case 'update-list':
        return { ...state, list: action.list };
      case 'edit-item':
        return { ...state, list: action.item };
      case 'add-item':
        const newList = state.list;
        newList.push(action.item);
        return { ...state, list: newList };
      default:
        return state;
    }
  };

  const [state, dispatch] = useReducer(reducer, initialValue);

  return (
    <TodoContext.Provider value={{ state, dispatch, API }}>
      {children}
    </TodoContext.Provider>
  );
};

export { TodoContext, TodoProvider };
