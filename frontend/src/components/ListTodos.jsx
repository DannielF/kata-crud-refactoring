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

  const deleteTodo = (id) => {
    fetch(api + `/${id}`, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
    }).then((id) => {
      dispatch({ type: 'delete-item', id });
    });
  };

  const editTodo = (todo) => {
    dispatch({ type: 'edit-item', item: todo });
  };

  return (
    <>
      <table>
        <thead>
          <tr>
            <td className="table-td">Id</td>
            <td className="table-td">Name</td>
            <td className="table-td">State</td>
          </tr>
        </thead>
        <tbody>
          {state.list.length > 0 ? (
            state.list.map((todo, index) => (
              <tr key={index}>
                <td className="table-td">{todo.id}</td>
                <td className="table-td">{todo.name}</td>
                <td className="table-td">
                  {todo.isCompleted ? 'True' : 'False'}
                </td>
                <td>
                  <button
                    className="table-button"
                    onClick={() => {
                      editTodo(todo);
                    }}
                  >
                    Edit
                  </button>
                </td>
                <td>
                  <button
                    className="table-button"
                    onClick={() => {
                      deleteTodo(todo.id);
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" className="table-td">
                No todos found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </>
  );
};

export { ListTodos };
