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
      <tr key={todo.id} className="border-b">
        <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
          {done && <FcApproval />}
          {todo.id}
        </td>
        <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
          {todo.name}
        </td>
        <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
          <input
            type="checkbox"
            defaultChecked={todo.done}
            onClick={() => {
              setDone(!done);
            }}
          ></input>
        </td>
        <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
          <button
            className="px-1 py-1 bg-red-700 rounded text-stone-100"
            onClick={() => {
              toDelete(todo.id);
            }}
          >
            Delete
          </button>
        </td>
        <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
          <button
            className="px-2 py-1 bg-lime-600 rounded"
            onClick={() => {
              setEditing(true);
            }}
          >
            Edit
          </button>
        </td>
        <td className="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
          {editing && <EditTodo setEdit={setEditing} todo={todo} />}
        </td>
      </tr>
    </>
  );
};

export { Todo };
