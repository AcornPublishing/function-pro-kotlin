package chapter11.exercises.ex4

import arrow.Kind
import chapter10.Cons
import chapter10.List
import chapter11.Functor
import utils.SOLUTION_HERE

interface Monad<F> : Functor<F> {

    fun <A> unit(a: A): Kind<F, A>
    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>

    override fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B> =
        flatMap(fa) { a -> unit(f(a)) }

    fun <A, B, C> map2(fa: Kind<F, A>, fb: Kind<F, B>, f: (A, B) -> C) =
        flatMap(fa) { a -> map(fb) { b -> f(a, b) } }

    fun <A> sequence(lfa: List<Kind<F, A>>): Kind<F, List<A>> =
        lfa.foldRight(
            unit(List.empty<A>()),
            { fa: Kind<F, A>, fla: Kind<F, List<A>> ->
                map2(fa, fla) { a: A, la: List<A> -> Cons(a, la) }
            }
        )

    //tag::init1[]
    fun <A> replicateM(n: Int, ma: Kind<F, A>): Kind<F, List<A>> =

        SOLUTION_HERE()
    //end::init1[]

    //tag::init2[]
    fun <A> _replicateM(n: Int, ma: Kind<F, A>): Kind<F, List<A>> =

        SOLUTION_HERE()
    //end::init2[]
}
