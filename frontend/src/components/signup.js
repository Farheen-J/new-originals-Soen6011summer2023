import React, { useState } from 'react';
import {
  Grid,
  Paper,
  Avatar,
  TextField,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  FormHelperText,
} from '@material-ui/core';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import PersonAddAltIcon from '@mui/icons-material/PersonAddAlt';
import { Alert } from '@mui/material';
import { makeStyles } from '@material-ui/core/styles';

import { signUp } from '../services/registerAPI';

const useStyles = makeStyles((theme) => ({
  paper: {
    padding: theme.spacing(4),
    width: '500px',
    margin: '0 auto',
  },
  avatar: {
    backgroundColor: theme.palette.primary.main,
    marginBottom: theme.spacing(2),
  },
  form: {
    width: '100%',
  },
  inputField: {
    marginBottom: theme.spacing(2),
  },
  submitButton: {
    margin: theme.spacing(2, 0),
    backgroundColor: theme.palette.primary.main,
    '&:hover': {
      backgroundColor: theme.palette.primary.dark,
    },
  },
  errorText: {
    color: 'red',
  },
}));

const Signup = ({ loginCallBack }) => {
  const classes = useStyles();
  const [errMsg, setErrMsg] = useState('');
  const [accountType, setAccountType] = useState('employer');

  const handleChange = (event) => {
    setAccountType(event.target.value);
  };

  const initialValues = {
    name: '',
    email_address: '',
    phone_number: '',
    password: '',
    confirmPassword: '',
    registration_number: '',
  };

  const validationSchema = Yup.object().shape({
    name: Yup.string().min(3, "It's too short").required('Required'),
    email_address: Yup.string().email('Enter valid email').required('Required'),
    phone_number: Yup.string().typeError('Enter valid Phone Number').required('Required'),
    password: Yup.string().min(8, 'Password minimum length should be 8').required('Required'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password')], 'Password not matched')
      .required('Required'),
    registration_number: Yup.string().when('accountType', {
      is: 'employer',
      then: Yup.string().length(5, 'Registration number is 5 characters long'),
      otherwise: Yup.string().notRequired(),
    }),
  });

  const onSubmit = (values, props) => {
    signUp({ ...values, age: 18, last_name: values.name, gender: 'male', userType: accountType })
      .then((data) => {
        if (data.errors) {
          setErrMsg(data.errors[0]);
        } else {
          loginCallBack();
          props.resetForm();
          setErrMsg('');
        }
      })
      .catch(() => {
        setErrMsg('Unable to register');
      })
      .finally(() => {
        props.setSubmitting(false);
      });
  };

  return (
    <Grid>
      <Paper className={classes.paper} elevation={0}>
        <Formik initialValues={initialValues} validationSchema={validationSchema} onSubmit={onSubmit}>
          {(props) => (
            <Form className={classes.form}>
              {errMsg !== '' && <Alert severity="error">{errMsg}</Alert>}
              <FormControl fullWidth className={classes.inputField}>
                <InputLabel id="account-type-label">Account Type</InputLabel>
                <Select
                  labelId="account-type-label"
                  id="account-type"
                  value={accountType}
                  onChange={handleChange}
                >
                  <MenuItem value="employer">Employer</MenuItem>
                  <MenuItem value="candidate">Candidate</MenuItem>
                </Select>
              </FormControl>
              <Field
                required
                as={TextField}
                fullWidth
                name="name"
                label="Name"
                placeholder="Enter your name"
                className={classes.inputField}
                error={props.errors.name && props.touched.name}
                helperText={<ErrorMessage name="name" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="email_address"
                label="Email"
                placeholder="Enter your email"
                className={classes.inputField}
                error={props.errors.email_address && props.touched.email_address}
                helperText={<ErrorMessage name="email_address" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="phone_number"
                label="Phone Number"
                placeholder="Enter your phone number"
                className={classes.inputField}
                error={props.errors.phone_number && props.touched.phone_number}
                helperText={<ErrorMessage name="phone_number" className={classes.errorText} />}
              />
              {accountType === 'employer' && (
                <>
                  <Field
                    as={TextField}
                    fullWidth
                    name="registration_number"
                    label="Registration Number"
                    placeholder="Enter your Registration number"
                    className={classes.inputField}
                    error={props.errors.registration_number && props.touched.registration_number}
                    helperText={
                      <ErrorMessage name="registration_number" className={classes.errorText} />
                    }
                  />
                </>
              )}
              <Field
                required
                as={TextField}
                fullWidth
                name="password"
                type="password"
                label="Password"
                placeholder="Enter your password"
                className={classes.inputField}
                error={props.errors.password && props.touched.password}
                helperText={<ErrorMessage name="password" className={classes.errorText} />}
              />
              <Field
                required
                as={TextField}
                fullWidth
                name="confirmPassword"
                type="password"
                label="Confirm Password"
                placeholder="Confirm your password"
                className={classes.inputField}
                error={props.errors.confirmPassword && props.touched.confirmPassword}
                helperText={<ErrorMessage name="confirmPassword" className={classes.errorText} />}
              />
              <Button
                fullWidth
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submitButton}
                disabled={props.isSubmitting}
              >
                {props.isSubmitting ? 'Loading' : 'Sign up'}
              </Button>
            </Form>
          )}
        </Formik>
      </Paper>
    </Grid>
  );
};

export default Signup;
