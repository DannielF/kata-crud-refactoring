import { useEffect, useState } from 'react';

const useFetch = (endpoint) => {
  const [data, setData] = useState();

  const fetchData = async (endpoint) => {
    let optionsFetch = {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'same-origin',
    };

    try {
      const response = await fetch(
        `http://localhost:8080${endpoint}`,
        optionsFetch
      );
      const data = await response.json();
      setData(data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchData(endpoint);
  }, []);

  return data;
};

export default useFetch;
