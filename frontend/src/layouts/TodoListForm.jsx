import React from 'react';
import { useForm } from 'react-hook-form';

const TodoListForm = ({ add }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data, event) => {
    add(data.name, event);
    event.target.reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="form-todo">
      <h3>Create a new list of todo</h3>
      <input
        className="form-todo__input"
        type="text"
        placeholder="TodoList name"
        name="name"
        {...register('name', {
          required: 'Required',
        })}
      />
      <span>{errors?.name?.message}</span>
      <button type="submit" className="form-todo__button">
        Save
      </button>
    </form>
  );
};

export { TodoListForm };
