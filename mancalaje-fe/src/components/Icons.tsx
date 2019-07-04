import React from "react";
import {useStyles} from "../theme";
import SvgIcon, {SvgIconProps} from "@material-ui/core/SvgIcon";
import {ListItem} from "@material-ui/core";

function HomeIcon(props: SvgIconProps) {
    return (
        <SvgIcon {...props}>
            <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/>
        </SvgIcon>
    );
}

function RemoveICon(props: SvgIconProps) {
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
            <path fill="none" d="M0 0h24v24H0V0z"/>
            <path
                d="M14.59 8L12 10.59 9.41 8 8 9.41 10.59 12 8 14.59 9.41 16 12 13.41 14.59 16 16 14.59 13.41 12 16 9.41 14.59 8zM12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z"/>
        </svg>
    )

}

export function RoomComponentIcon() {
    const classes = useStyles();
    return (
        <HomeIcon className={classes.icon} color="secondary"/>
    )
}

export function RemoveComponentIcon() {
    const classes = useStyles();
    return (
        <RemoveICon className={classes.icon} color="secondary"/>
    )
}

export function ListItemLink(props: any) {
    return <ListItem button component="a" {...props} />;
}

