import React, { useEffect } from 'react';

const ListTodos = ({ dispatch, state, api }) => {
  useEffect(() => {
    fetch(api, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
    })
      .then((res) => res.json())
      .then((list) => {
        dispatch({ type: 'update-list', list });
      });
  }, []);
  return (
    <>
      <table>
        <thead>
          <tr>
            <td>Id</td>
            <td>Name</td>
            <td>State</td>
          </tr>
        </thead>
        <tbody>
          {state.list.length > 0 ? (
            state.list.map((todo, index) => (
              <tr key={index}>
                <td>{todo.id}</td>
                <td>{todo.name}</td>
                <td>{todo.isCompleted ? 'True' : 'False'}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No todos found</td>
            </tr>
          )}
        </tbody>
      </table>
    </>
  );
};

export { ListTodos };
