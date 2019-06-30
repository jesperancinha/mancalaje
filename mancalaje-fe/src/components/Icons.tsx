import React from "react";
import {useStyles} from "../theme";
import SvgIcon, {SvgIconProps} from "@material-ui/core/SvgIcon";

function HomeIcon(props: SvgIconProps) {
    return (
        <SvgIcon {...props}>
            <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/>
        </SvgIcon>
    );
}

export function RoomComponentIcon() {
    const classes = useStyles();
    return (
        <HomeIcon className={classes.icon} color="secondary"/>
    )
}