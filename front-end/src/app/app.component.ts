import { Component } from '@angular/core';
import {Book} from "./dto/book";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'front-end';
  bookList: Array<Book> = [];
  isDone: boolean = false;
  status: string = '';
  description: string= '';
  isIsbnToEdit: boolean= false;

  constructor(private httpClient: HttpClient) {
    httpClient.get<Array<Book>>('http://localhost:8080/api/v1/books').subscribe(bookList => {
      this.bookList = bookList;
    });
  }

  deleteBook(book: Book) {
    this.httpClient.delete(`http://localhost:8080/api/v1/books/${book.isbn}`).subscribe(value => {
      const index = this.bookList.indexOf(book);
      this.bookList.splice(index, 1);
      this.showToast(true,"Deleting Done",`Deleting of the book ${book.isbn} is done!`);
    });
  }

  editBook(book: Book, isbn: HTMLInputElement, title: HTMLInputElement) {
    if(!title.value.trim()){
      title.select();
      return
    }
    if(!isbn.value.trim()){
      title.select();
      this.isIsbnToEdit = true;
      return
    }
    isbn.value = book.isbn;
    let book1:Book = new Book(book.isbn,title.value.trim());
    this.httpClient.put(`http://localhost:8080/api/v1/books`,book1).subscribe(value => {
        const index = this.bookList.indexOf(book);
        this.bookList[index] = book1;
      this.showToast(true,"Editing Done",`Editing of the book ${book.isbn} is done!`);
    })
    isbn.value ='';
    title.value='';
  }

  saveBook(isbn: HTMLInputElement, title: HTMLInputElement) {
    if(!title.value.trim()){
      title.select();
      return
    }
    if(!title.value.trim()){
      title.select();
      return
    }
    let book:Book = new Book(isbn.value.trim(),title.value.trim());
    this.httpClient.post<Book>('http://localhost:8080/api/v1/books',book).subscribe(value => {
      this.bookList.push(book);
      this.showToast(true,"Saving Done",`Saving of the book ${book.isbn} is done!`);
      isbn.value = '';
      title.value = '';
    })
  }

  toastClose() {
    this.isDone =false;
  }
  showToast(isDone:boolean,status:string,description:string){
    this.isDone =isDone;
    this.status = status;
    this.description = description;
    setTimeout(()=>{
      this.isDone = false;
      this.status = '';
      this.description = '';
    },3000);
  }
}
