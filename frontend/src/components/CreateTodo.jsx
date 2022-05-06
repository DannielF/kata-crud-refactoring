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
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="flex flex-col mt-5 items-center w-full"
    >
      <h3 className="text-xl font-semibold">Create a new todo</h3>
      <input
        className="w-full mt-2 p-2 border-2 border-gray-600 rounded-lg"
        type="text"
        placeholder="What needs to be done?"
        name="name"
        {...register('name', {
          required: 'Required',
        })}
      />
      <span className="text-red-600">{errors?.name?.message}</span>
      <button className="w-3/12 py-2 mt-5 bg-lime-600 rounded" type="submit">
        Save
      </button>
    </form>
  );
};

export { CreateTodo };
