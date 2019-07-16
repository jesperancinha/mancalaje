import * as React from "react";
import {Grid, Typography} from "@material-ui/core";
import AppBar from "@material-ui/core/AppBar";
import Box from "@material-ui/core/Box";

class MancalaJeHeader extends React.Component {
    render() {
        return (
            <Grid
                container
                spacing={0}
                direction="column"
                alignItems="center"
                justify="center"
                style={{minHeight: '100vh'}}>
                <Grid item xs={12}>
                    <Box margin={2}>
                        <AppBar title="Title" position="relative">
                            <Typography align="center" component="h1" variant="h1">MancalaJe</Typography>
                        </AppBar>
                    </Box>
                </Grid>
                <Grid item xs={12}/>
                {this.props.children}
            </Grid>
        )
    }
}

export {MancalaJeHeader};

