import * as React from 'react';
import Box from '@mui/material/Box';



const width = 700;

const widthModifier = {
  width: `${width}px`,
  fontWeight: "800",
  color: 'var(--main-color)'
};

export default function CandidateHP() {
  const [value, setValue] = React.useState('1');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };


  return (
    <Box sx={{ width: '500%', typography: 'body1' }}>
        <div> Candidate </div>
    </Box>
  );
}