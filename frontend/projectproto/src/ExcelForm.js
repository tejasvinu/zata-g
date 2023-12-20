import React, { useState } from 'react';
import axios from 'axios';

const ExcelUploadForm = () => {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (file) {
      try {
        const formData = new FormData();
        formData.append('file', file);

        await axios.post('http://localhost:8080/upload-excel', formData);

        setMessage('Upload successful');
      } catch (error) {
        setMessage('Error uploading file');
        console.error(error);
      }
    } else {
      setMessage('Please select a file');
    }
  };

  return (
    <div>
      <h2>Excel File Upload</h2>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Upload</button>
      {message && <p>{message}</p>}
    </div>
  );
};

export default ExcelUploadForm;
