# redirect

##### Web application for creating personal redirection pages. Using Java/Spring-Boot/MySQL and Vaadin as a frontend framework.

##### Apache 2.0 License - feel free to fork / use!

## Current application state:

POC, currently in development

<u>Features:</u>

- [ ] Simple front page with edition functionality
- [ ] Card creation with custom header, email, phone and quote

## Terminal Commands

### `exit`
Exits the application.
- **Usage**: `exit`

### `debug`
Toggles the debug mode on or off.
- **Usage**: `debug`

### `cardinfomanager`
Allows entering card information (header, email, phone, quote) for database insertion.
- **Usage**: `cardinfomanager`
    - **Parameters**:
        - **header**: Text for the card header.
        - **email**: Email address for the card.
        - **phone**: Phone number for the card.
        - **quote**: Quote to display on the card.
    - **Example**:
      ```
      redirect :3 >cardinfomanager
      Enter header: My Header
      Enter email: example@example.com
      Enter phone: 1234567890
      Enter quote: This is a quote.
      ```