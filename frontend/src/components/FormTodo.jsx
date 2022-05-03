import React from 'react';
import { useForm } from 'react-hook-form';

const FormTodo = ({ dispatch, api }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = (data) => {
    const request = {
      id: null,
      name: data.name,
      isCompleted: false,
    };

    fetch(api, {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
      body: JSON.stringify(request),
    })
      .then((res) => res.json())
      .then((data) => {
        dispatch({ type: 'add-item', item: data });
      });
  };

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          type="text"
          name="name"
          {...register('name', {
            required: 'Required',
          })}
        />
        <div>{errors?.name?.message}</div>
        <button type="submit">Add</button>
      </form>
    </>
  );
};

export { FormTodo };
