import React from 'react';
import { useForm } from 'react-hook-form';

const FormTodo = ({ dispatch, state: { item }, api }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    setValue,
  } = useForm({
    defaultValues: item,
  });

  setValue('name', item.name);

  const onSubmit = (data, event) => {
    const request = {
      name: data.name,
      isCompleted: false,
    };
    event.target.reset();

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

  const editTodo = (data) => {
    const request = {
      id: item.id,
      name: data.name,
      isCompleted: item.isCompleted,
    };
    fetch(api + `/${item.id}`, {
      method: 'PUT',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
      body: JSON.stringify(request),
    })
      .then((res) => res.json())
      .then((data) => {
        dispatch({ type: 'update-item', item: data });
      });
  };

  return (
    <>
      {item.id ? (
        <form onSubmit={handleSubmit(editTodo)} className="form-todo">
          <input
            className="form-todo__input"
            type="text"
            name="name"
            {...register('name', {
              required: 'Required',
            })}
          />
          <div>{errors?.name?.message}</div>
          <button className="form-todo__button" type="submit">
            Update
          </button>
        </form>
      ) : (
        <form onSubmit={handleSubmit(onSubmit)} className="form-todo">
          <input
            className="form-todo__input"
            type="text"
            name="name"
            placeholder="What needs to be done?"
            {...register('name', {
              required: 'Required',
            })}
          />
          <div>{errors?.name?.message}</div>
          <button className="form-todo__button" type="submit">
            Add
          </button>
        </form>
      )}
    </>
  );
};

export { FormTodo };
