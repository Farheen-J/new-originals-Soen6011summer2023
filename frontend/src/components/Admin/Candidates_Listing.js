import React, { useState, useEffect  } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { deleteCandidate } from '../../services/registerAPI';
import { candidateListings } from '../../services/registerAPI';

const useStyles = makeStyles(theme => ({
    userList: {
        marginTop: theme.spacing(2),
    },
    userListContainer: {
        marginTop: theme.spacing(2),
    },
    userItem: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: theme.spacing(2),
        borderBottom: '1px solid #ccc',
        backgroundColor: '#fffdfa', // Set the background color for all user items
        borderRadius: theme.spacing(1), // Adjust the value as needed
        cursor: 'pointer', // Add cursor: pointer to make the userItem hover on cursor
        '&:hover': {
            backgroundColor: '#ffe4e6', // Change background color on hover
        },

    },
    userEmail: {
        //fontWeight: 'bold',
        color: '#000000', // Set the color for all email addresses
    },
    deleteUserButton: {
        color: 'red',
        cursor: 'pointer',
    },
    searchContainer: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        marginBottom: theme.spacing(3),
    },
    searchIcon: {
        marginRight: theme.spacing(1),
    },
    errorMsg: {
        backgroundColor: '#fffdfa',
        color: 'red',
        padding: theme.spacing(1),
        marginBottom: theme.spacing(2),
        marginBottom: theme.spacing(2),
        borderRadius: theme.spacing(1), // Adjust the value as needed
      },
}));

function AdminTrackingPanel({ data }) {
    const classes = useStyles();

    useEffect(() => {
        candidateListings();
        }, []); // Call the function when the component mounts
        
    const initialUserList = data;
    const [errMsg, setErrMsg] = useState('');
    const [userList, setUserList] = useState(initialUserList);

    const [searchTerm, setSearchTerm] = useState('');

    const fetchDataFromAPI = async (userData) => {
        deleteCandidate(userData.email_address)
            .then((data) => {
                console.log("Delete " + JSON.stringify(data))
                if (data.errors) {
                    setErrMsg(data.errors[0]);
                } else {
                    const updatedUserList = userList.filter(user => user.id !== userData.id);
                    setUserList(updatedUserList);
                    setErrMsg(data.data);
                }
            })
            .catch(() => {
                setErrMsg('Unable to register');
            });
    };

    const handleDeleteUser = (id) => {
        fetchDataFromAPI(id);
        // const updatedUserList = userList.filter(user => user.id !== id);
        //setUserList(updatedUserList);
    };

    const handleSearch = (event) => {
        const searchValue = event.target.value;
        setSearchTerm(searchValue);

        // Filter the initialUserList based on the search term
        const filteredList = initialUserList.filter(user =>
            user.email_address.includes(searchValue.toLowerCase())
        );

        setUserList(filteredList);
    };

    return (
        <div className={classes.userList}>
            <div className={classes.searchContainer}>
                <svg
                    className={classes.searchIcon}
                    width="16"
                    height="16"
                    viewBox="0 0 16 16"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                >
                    <path
                        fill-rule="evenodd"
                        clip-rule="evenodd"
                        d="M11.7998 10.7998H12.4998L15.6998 14.9998L14.2998 16.3998L10.0998 12.1998V11.4998L9.84979 11.2498C8.99979 12.0998 7.79979 12.5998 6.49979 12.5998C3.14979 12.5998 0.499786 9.94976 0.499786 6.59976C0.499786 3.24976 3.14979 0.599762 6.49979 0.599762C9.84979 0.599762 12.4998 3.24976 12.4998 6.59976C12.4998 7.89976 11.9998 9.09978 11.1498 9.94978L11.3998 10.1998H11.7998V10.7998ZM6.49979 10.5998C8.82979 10.5998 10.6998 8.72977 10.6998 6.59976C10.6998 4.46976 8.82979 2.59976 6.49979 2.59976C4.36979 2.59976 2.49979 4.46976 2.49979 6.59976C2.49979 8.72977 4.36979 10.5998 6.49979 10.5998Z"
                        fill="#007bff"
                    />
                </svg>
                {/* Add the search input */}
                <input
                    type="text"
                    value={searchTerm}
                    onChange={handleSearch}
                    placeholder="Search by email"
                />
            </div>
            {errMsg && (
                <div  className={classes.errorMsg}>
                    {errMsg}
                </div>
            )}
            {userList.map(user => (
                <div key={user.id} className={classes.userItem}>
                    <div className={classes.userEmail}>{user.email_address}</div>
                    <div
                        className={classes.deleteUserButton}
                        onClick={() => handleDeleteUser(user)}
                    >
                        Delete
                    </div>
                </div>
            ))}
        </div>
    );
}

export default AdminTrackingPanel;
