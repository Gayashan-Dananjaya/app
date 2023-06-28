import express from "express";
import mysql, {Pool} from 'promise-mysql';

export const router = express.Router();

let pool: Pool;

initPool();

async function initPool() {
    pool = await mysql.createPool({
        host: 'localhost',
        port: 3306,
        database: 'dep10_microservices',
        user: 'root',
        password: 'Gayashan@1996'
    });
}

type book = {
    isbn: string;
    title: string;
}

/*Delete book*/
router.delete('/api/v1/books', async (req, res) => {
    const book = (req.body as book);
    const bool = await validate(book);
    if (!bool) {
        res.sendStatus(404);
        return;
    };
    const result = await pool.query('DELETE FROM book WHERE isbn=?', [book.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});

/*Update book*/
router.patch('/api/v1/books', async (req, res) => {
    const book = (req.body as book);
    if (!book.title) {
        res.sendStatus(400);
        return;
    }
    const result = await pool.query('UPDATE book SET title=? WHERE isbn=?', [book.title, book.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
});

// Validation
async function validate(book: book) {
    const response = await pool.query('SELECT * FROM book WHERE isbn=?', [book.isbn]);
    if (response[0] === undefined) {
        return false;
    }
    if (book.title !== response[0].title) {
        return false;
    }
    return true;
}