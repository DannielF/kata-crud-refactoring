import React from 'react';
import { useForm } from 'react-hook-form';
import { TodoContext } from '../context/index';

/**
 * Create a new todo
 * @param {*} list Object
 * @returns {JSX.Element}
 */
const CreateTodo = (list) => {
  const { addTodo } = React.useContext(TodoContext);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data, event) => {
    addTodo(data, list);
    event.target.reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h3>Create a new todo</h3>
      <input
        type="text"
        placeholder="What needs to be done?"
        name="name"
        {...register('name', {
          required: 'Required',
        })}
      />
      <span>{errors?.name?.message}</span>
      <button type="submit">Save</button>
    </form>
  );
};

export { CreateTodo };
