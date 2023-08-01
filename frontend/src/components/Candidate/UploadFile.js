import React, {useState} from 'react';
import axios from 'axios';

function UploadFile() {

  const [file, setFile] = useState()

  function handleChange(event) {
    setFile(event.target.files[0])
  }

  function handleSubmit(event) {
    event.preventDefault()
    const url = 'http://localhost:8080/v1/upload_resume';
    const formData = new FormData();
    formData.append('uploaded_resume', file);
    formData.append('email_address', 'arshiyasahni87@gmail.com');

    const config = {
      headers: {
        'content-type': 'multipart/form-data',
      },
    };
    axios.post(url, formData, config).then((response) => {
      console.log(response.data);
    });

  }

  return (
    <div className="App">
        <form onSubmit={handleSubmit}>
          <h1>Upload Your Resume</h1>
          <input type="file" onChange={handleChange}/>
          <button type="submit">Upload</button>
        </form>
    </div>
  );
}

export default UploadFile;