import React, {useState} from 'react';
import axios from 'axios';
import Success from "./Form/Success";

function UploadFile() {

  const [file, setFile] = useState();
  const [success, setSuccess] = useState(false);
  function handleChange(event) {
    setFile(event.target.files[0])
  }

  function handleSubmit(event) {
    event.preventDefault()
    const url = 'http://localhost:8080/uploadFile';
    const formData = new FormData();
    formData.append('file', file);
    formData.append('fileName', file.name);
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
          <button type="submit" onClick={() => {setSuccess(true );}}>Upload</button>
          {success && <Success />}
        </form>
    </div>
  );
}

export default UploadFile;