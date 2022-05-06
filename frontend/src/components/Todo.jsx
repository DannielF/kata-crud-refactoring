import React from 'react';
import { TodoContext } from '../context/index';
import { EditTodo } from './EditTodo';
import { FcApproval } from 'react-icons/fc';

/**
 * Table to show the list of todo
 * @param {*} props todo
 * @returns {JSX.Element}
 */
const Todo = (todo) => {
  const { deleteTodo } = React.useContext(TodoContext);
  const [editing, setEditing] = React.useState(false);
  const [done, setDone] = React.useState(todo.done);

  const toDelete = (id) => {
    deleteTodo(id);
  };

  return (
    <>
      <tr key={todo.id}>
        <td>{todo.id}</td>
        <td>
          {todo.name}
          {done && <FcApproval />}
        </td>
        <td>
          <input
            type="checkbox"
            defaultChecked={todo.done}
            onClick={() => {
              setDone(!done);
            }}
          ></input>
        </td>
        <td>
          <button
            onClick={() => {
              toDelete(todo.id);
            }}
          >
            Delete
          </button>
        </td>
        <td>
          <button
            onClick={() => {
              setEditing(true);
            }}
          >
            Edit
          </button>
          {editing && <EditTodo setEdit={setEditing} todo={todo} />}
        </td>
      </tr>
    </>
  );
};

export { Todo };
