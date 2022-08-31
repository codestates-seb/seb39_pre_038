import { useEffect, useState, useCallback } from 'react';
import axios from 'axios';

function useFetch({ url = '' }) {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);
  const [isLoding, seTisLoding] = useState(false);

  const getData = useCallback(async () => {
    const response = await (await axios.get(url)).data;
    setData(response);
  }, [url]);

  useEffect(() => {
    getData().catch((err) => setError(err.message));
    seTisLoding(true);
  }, [data, error, getData]);

  return { data, setData, isLoding, error };
}

export default useFetch;
