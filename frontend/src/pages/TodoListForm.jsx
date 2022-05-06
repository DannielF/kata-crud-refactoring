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
    <form
      onSubmit={handleSubmit(onSubmit)}
      className="flex flex-col mt-20 items-center w-1/2"
    >
      <h3 className="text-xl font-semibold">Create a new list of todo</h3>
      <input
        className="w-1/2 mt-2 p-2 border-2 border-gray-600 rounded-lg"
        type="text"
        placeholder="TodoList name"
        name="name"
        {...register('name', {
          required: 'Required',
        })}
      />
      <span className="text-red-600">{errors?.name?.message}</span>
      <button type="submit" className="w-3/12 py-2 mt-5 bg-lime-600 rounded">
        Save
      </button>
    </form>
  );
};

export { TodoListForm };
