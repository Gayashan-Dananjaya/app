import express, {json} from 'express';
import cors from 'cors';
import {router} from './api/controller';

const app = express();

app.use(cors());
app.use(json());
app.use(router);
app.listen(8081, () => {console.log('Server has been started at 8081')})