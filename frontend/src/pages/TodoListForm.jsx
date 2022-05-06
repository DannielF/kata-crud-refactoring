import React from 'react';
import { useForm } from 'react-hook-form';

/**
 * Form to create a new todo list
 * @param {*} props function
 * @returns {JSX.Element}
 */
const TodoListForm = ({ addTodoList }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data, event) => {
    addTodoList(data.name);
    event.target.reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h3>Create a new list of todo</h3>
      <input
        type="text"
        placeholder="TodoList name"
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

export { TodoListForm };
