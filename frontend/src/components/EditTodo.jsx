import React from 'react';
import { useForm } from 'react-hook-form';
import { TodoContext } from '../context/index';

/**
 * Edit the info of a todo
 * @param {*} props state, Object
 * @returns {JSX.Element}
 */
const EditTodo = ({ setEdit, todo }) => {
  const { editTodo } = React.useContext(TodoContext);

  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm({
    defaultValues: { todo },
  });

  setValue('name', todo.name);

  const onSubmit = (data, event) => {
    editTodo(data, todo);
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
