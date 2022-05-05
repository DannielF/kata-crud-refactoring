import React from 'react';

const ListTodo = ({ list, todos }) => {
  return (
    <section>
      <div>
        <h2></h2>
        <button>Delete list</button>
      </div>
      <table>
        <th>
          <tr>
            <td>Id</td>
            <td>Task</td>
            <td>Done</td>
          </tr>
        </th>
        <tbody>{}</tbody>
      </table>
    </section>
  );
};

export { ListTodo };
