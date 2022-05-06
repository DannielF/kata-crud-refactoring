import React from 'react';
import { CreateTodo } from './CreateTodo';
import { Todo } from './Todo';
import { TodoContext } from '../context/index';

const ListTodo = ({ list, todos }) => {
  const { deleteTodoList } = React.useContext(TodoContext);

  const toDelete = (id) => {
    deleteTodoList(id);
  };

  return (
    <section>
      <div>
        <h2>{list.name}</h2>
        <button
          onClick={() => {
            toDelete(list.id);
          }}
        >
          Delete list
        </button>
        <CreateTodo {...list} />
      </div>
      <table>
        <thead>
          <tr>
            <td>Id</td>
            <td>Task</td>
            <td>Done</td>
          </tr>
        </thead>
        <tbody>
          {console.log(todos)}
          {todos.map((todo, index) => (
            <Todo key={index} {...todo} />
          ))}
        </tbody>
      </table>
    </section>
  );
};

export { ListTodo };
