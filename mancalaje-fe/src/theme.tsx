import createMuiTheme from "@material-ui/core/styles/createMuiTheme";
import {blue, green, red} from "@material-ui/core/colors";


const theme = createMuiTheme({
    typography: {
        fontFamily: [
            'Raleway',
            '-apple-system',
            'BlinkMacSystemFont',
            '"Segoe UI"',
            'Roboto',
            '"Helvetica Neue"',
            'Arial',
            'sans-serif',
            '"Apple Color Emoji"',
            '"Segoe UI Emoji"',
            '"Segoe UI Symbol"',
        ].join(','),
        fontSize: 10
    },
    palette: {
        primary: {main: blue[50]},
        secondary: {main: green[100]},
        error: red,
        contrastThreshold: 3,
        tonalOffset: 0.2,
        type: "light",
        background: {default: '#fff'}

    }
});

export default theme;