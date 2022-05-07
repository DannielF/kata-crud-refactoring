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
    setValue
  } = useForm({
    defaultValues: { todo }
  });

  setValue('name', todo.name);

  const onSubmit = (data, event) => {
    editTodo(data, todo);
    event.target.reset();
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col items-center">
      <input
        className="border-2 border-gray-600 rounded"
        type="text"
        placeholder="New todo name"
        name="name"
        {...register('name', {
          required: 'Required'
        })}
      />
      <span>{errors?.name?.message}</span>
      <div className="mt-2">
        <button type="submit" className="py-0.5 px-1.5 bg-lime-600 rounded">
          Edit
        </button>{' '}
        <button
          className="py-0.5 px-1.5 bg-red-700 rounded"
          onClick={() => {
            setEdit(false);
          }}
        >
          Cancel
        </button>
      </div>
    </form>
  );
};

export { EditTodo };
