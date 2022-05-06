import React from 'react';
import { useForm } from 'react-hook-form';
import { TodoContext } from '../context/index';

const EditTodo = ({ setEdit, id }) => {
  const { editTodo } = React.useContext(TodoContext);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data, event) => {
    editTodo(data, id);
    event.target.reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input
        type="text"
        placeholder="New todo name"
        name="name"
        {...register('name', {
          required: 'Required',
        })}
      />
      <span>{errors?.name?.message}</span>
      <button type="submit">Edit</button>
      <button
        onClick={() => {
          setEdit(false);
        }}
      >
        Cancel
      </button>
    </form>
  );
};

export { EditTodo };
