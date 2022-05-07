import React from 'react';
import { CreateTodo } from './CreateTodo';
import { Todo } from './Todo';
import { TodoContext } from '../context/index';

/**
 * List todoList and todo items
 * @param {*} props Object
 * @returns {JSX.Element}
 */
const ListTodo = ({ list, todos }) => {
  const { deleteTodoList } = React.useContext(TodoContext);

  const toDelete = (id) => {
    deleteTodoList(id);
  };

  return (
    <section className="flex flex-col items-center mt-3 mb-10 w-full">
      <div className="pt-3">
        <div className="flex justify-center items-baseline">
          <h2 className="text-xl py-1 px-5 font-semibold text-center bg-orange-600 rounded mb-3">
            {list.name}
          </h2>
          <button
            className="w-3/12 py-1 bg-red-700 rounded ml-3 text-stone-100"
            onClick={() => {
              toDelete(list.id);
            }}
          >
            Delete
          </button>
        </div>
        <CreateTodo {...list} />
      </div>
      {todos.length != 0 && (
        <table className="min-w-full mt-10">
          <thead className="border-b">
            <tr>
              <td scope="col" className="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                Id
              </td>
              <td scope="col" className="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                Task
              </td>
              <td scope="col" className="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                Done
              </td>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo, index) => (
              <Todo key={index} {...todo} />
            ))}
          </tbody>
        </table>
      )}
    </section>
  );
};

export { ListTodo };
