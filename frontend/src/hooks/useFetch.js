import { useEffect, useState } from 'react';
import axios from 'axios';

function useFetch(url = '') {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);
  const [isLoding, setIsLoding] = useState(true);

  useEffect(() => {
    axios
      .get(url)
      .then((res) => {
        setData(res.data);
        setIsLoding(false);
      })
      .catch((err) => {
        setError(err.message);
        setIsLoding(false);
      });
  }, [url]);

  return { data, setData, isLoding, error };
}

export default useFetch;
